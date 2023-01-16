package com.example.simon;

import static com.example.simon.Movement.randomEnum;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class Round {
    private final Integer number;
    private final State state;
    private final Question question;

    public Round(Integer number, State state, Question question) {
        this.number = number;
        this.state = state;
        this.question = question;
    }

    public Integer getNumber() {
        return number;
    }

    public State getState() {
        return state;
    }

    public Question getQuestion() {
        return question;
    }
}

public class SharedViewModel extends ViewModel {

    private final static int ANSWER_TIMEOUT = 5_000;
    private final static int DOWN_TIME = 3_000;

    // win, game over, answering, downtime
    private final MutableLiveData<Round> _state = new MutableLiveData<>(new Round(-1, State.GAME_OVER, null));

    public LiveData<Round> getState() {
        return _state;
    }

    private Timer timer;

    private final List<Question> questions = new LinkedList<>();
    private Question currentQuestion = null;

    private int rounds = 5;
    private int currentRound = 0;

    public void simonAsk(int maxRounds) {
        // reset state in case a new game is started
        rounds = maxRounds;
        currentRound = 0;
        currentQuestion = null;
        questions.clear();
        if (timer != null) {
            timer.cancel();
        }

        // create first question, always Action
        Question first = new ActionQuestion(randomEnum(Movement.class), currentRound);
        questions.add(first);
        currentQuestion = first;

        Log.d("simon_game", String.format("Starting game with question [%s]", first.getQuote()));

        // change state to answering, notify activity
        Round round = new Round(currentRound, State.ANSWERING, first);
        _state.postValue(round);

        // start answer timer
        timer = new Timer("simon_says");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO this should end the game, user took too long
                Log.d("simon_game", "Answer timeout");

                _state.postValue(new Round(currentRound, State.GAME_OVER, first));
            }
        }, ANSWER_TIMEOUT);
    }

    public void answer(Movement movement) {
        if (_state.getValue() == null || _state.getValue().getState() != State.ANSWERING) {
            Log.e("simon_answer", "The game is not expecting any answer");
            return;
        }

        if (currentQuestion == null) {
            Log.e("simon_answer", "There is no current question to answer");
            _state.setValue(new Round(currentRound, State.GAME_OVER, currentQuestion));
            return;
        }

        timer.cancel();

        // validate current question
        Boolean correct = currentQuestion.answer(movement);
        Log.d("simon_game", String.format("Answer: %s, correct: %b", movement.name(), correct));

        // change state to failed if wrong answer
        if (!correct) {
            Log.d("simon_game", "Game over");
            _state.postValue(new Round(currentRound, State.GAME_OVER, currentQuestion));
        } else {
            // change state to downtime until next round
            _state.postValue(new Round(currentRound, State.DOWNTIME, currentQuestion));
            Log.d("simon_game", "Delaying next round..");

            // start downtime timer
            timer = new Timer("simon_says");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    nextRound();
                }
            }, DOWN_TIME);
        }
    }

    private void nextRound() {
        // win game if current rounds == maxRounds
        currentRound += 1;
        if (currentRound >= rounds) {
            Log.d("simon_game", "Game won, all rounds completed");
            _state.postValue(new Round(currentRound, State.WIN, currentQuestion));
            return;
        }

        // if game is not over, generate next question
        Question nextQuestion = generateQuestion();
        questions.add(nextQuestion);
        currentQuestion = nextQuestion;

        Log.d("simon_game", String.format("Round %d with question [%s]", currentRound + 1, nextQuestion.getQuote()));

        // change state to answering
        _state.postValue(new Round(currentRound, State.ANSWERING, currentQuestion));

        // start timer
        timer = new Timer("simon_says");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO this should end the game, user took too long
                Log.d("simon_game", "Answer timeout");

                _state.postValue(new Round(currentRound, State.GAME_OVER, currentQuestion));
            }
        }, ANSWER_TIMEOUT);
    }

    private Question generateQuestion() {
        int randomQuestion = random(2);

        if (randomQuestion == 0) {
            return new ActionQuestion(randomEnum(Movement.class), currentRound);
        } else {
            Question toRepeat = questions.get(random(questions.size()));
            return new RepeatQuestion(toRepeat, currentRound);
        }
    }

    private int random(int max) {
        return (int)(Math.random() * max);
    }
}

enum State {
    WIN, GAME_OVER, ANSWERING, DOWNTIME
}

enum Movement {
    UP, DOWN, LEFT, RIGHT;

    Movement opposite() {
        if (this == Movement.UP) {
            return DOWN;
        } else if (this == Movement.DOWN) {
            return UP;
        } else if (this == Movement.LEFT) {
            return RIGHT;
        } else {
            return LEFT;
        }

    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int size = clazz.getEnumConstants().length;
        int random = (int) (Math.random() * size);
        return clazz.getEnumConstants()[random];
    }
}

interface Question {
    int getId();

    String getQuote();

    Boolean answer(Movement movement);
}

class ActionQuestion implements Question {

    private final Movement movement;
    private final int id;

    ActionQuestion(Movement movement, int id) {
        this.movement = movement;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getQuote() {
        return "Faz movimento para " + movement.name();
    }

    @Override
    public Boolean answer(Movement movement) {
        return this.movement == movement;
    }

    @Override
    public String toString() {
        return "ActionQuestion{" +
                "quote=" + getQuote() +
                ", id=" + id +
                '}';
    }
}

class RepeatQuestion implements Question {

    private final Question question;
    private final int id;

    RepeatQuestion(Question question, int id) {
        this.question = question;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getQuote() {
        return "Repete o que fizeste no turno " + (question.getId() + 1) ;
    }

    @Override
    public Boolean answer(Movement movement) {
        return question.answer(movement);
    }

    @Override
    public String toString() {
        return "ActionQuestion{" +
                "quote=" + getQuote() +
                ", id=" + id +
                '}';
    }
}

class OppositeQuestion implements Question {

    private final Question question;
    private final int id;

    OppositeQuestion(Question question, int id) {
        this.question = question;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getQuote() {
        return "Faz o oposto do que fizeste no turno " + question.getId() + 1;
    }

    @Override
    public Boolean answer(Movement movement) {
        return question.answer(movement.opposite());
    }


}
