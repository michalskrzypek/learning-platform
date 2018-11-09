package pl.michalskrzypek.LearningPlatform.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(String tagName){
        super("Tag: " + tagName + " could not be found");
    }
}
