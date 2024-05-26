package model;

import java.util.Objects;

import org.apache.commons.text.similarity.LevenshteinDistance;

/**
 * Represents a question of a BasicCard
 */
public class Question {
    private String author; 
    private Theme theme; 
    private String subject;
    private String challenge;
    private String answer;

    /**
     * Constructs a new Question object with the specified author, theme, subject, challenge, and answer
     *
     * @param author    The author of the question's card
     * @param theme     The theme of the question's card
     * @param subject   The subject of the question's card
     * @param challenge The question text
     * @param answer    The correct answer to the question
     */
    public Question(String author, Theme theme, String subject, String challenge, String answer) {
        this.author = author;
        this.theme = theme;
        this.subject = subject;
        this.challenge = challenge;
        this.answer = answer;
    }

    /**
     * Constructs a new Question object for DeckDuel with the specified challenge and answer
     *
     * @param challenge The question text
     * @param answer    The correct answer to the question
     */
    public Question(String challenge, String answer) {
        this.challenge = challenge;
        this.answer = answer;
    }

    /**
     * Sets the challenge text of the question
     *
     * @param challenge The challenge text to be set
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    /**
     * Sets the correct answer to the question
     *
     * @param answer The correct answer to be set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Checks if the provided answer matches the correct answer with a tolerance of one edit distance with Levenshtein algorithm
     *
     * @param answerPlayer The answer provided by the player.
     * @return true if the provided answer is correct (with a tolerance of one edit distance), false otherwise
     */
    public boolean checkAnswer(String answerPlayer) {
        LevenshteinDistance levenshteinDistance = LevenshteinDistance.getDefaultInstance();
        int distance = levenshteinDistance.apply(answerPlayer.toLowerCase(), answer.toLowerCase());

        return distance <= 1;
    }

    /**
     * Retrieves a string representation of the question
     *
     * @return A string representation of the question including author, theme, subject, challenge, and answer
     */
    @Override
    public String toString() {
        return "author : " + author + "\ntheme : " + theme + "\nsubject : " + subject + "\nchallenge : " + challenge
                + "\nanswer : " + answer + "\n";
    }

    /**
     * Retrieves the author of the question's card
     *
     * @return The author of the question's card
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retrieves the theme of the question's card
     *
     * @return The theme of the question's card
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Retrieves the subject of the question's card
     *
     * @return The subject of the question's card
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Retrieves the text of the question
     *
     * @return The text of the question
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * Retrieves the correct answer to the question
     *
     * @return The correct answer to the question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Generates a hash code for the question based on its theme, subject, and answer
     *
     * @return The hash code of the question
     */
    @Override
    public int hashCode() {
        return Objects.hash(answer, subject, theme);
    }

    /**
     * Checks if this question is equal to another object
     *
     * @param obj The object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question other = (Question) obj;
        return Objects.equals(answer, other.answer) && Objects.equals(subject, other.subject) && theme == other.theme;
    }

    /**
     * Creates and returns a clone of this question
     *
     * @return A clone of this question
     */
    public Question clone() {
        return new Question(author, theme, subject, challenge, answer);
    }
}
