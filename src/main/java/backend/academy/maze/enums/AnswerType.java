package backend.academy.maze.enums;

import lombok.Getter;

@Getter
public enum AnswerType {
    YES("y"), NO("n");

    AnswerType(String answer) {
        this.answer = answer;
    }

    private String answer;
}
