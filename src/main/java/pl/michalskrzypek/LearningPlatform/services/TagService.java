package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.Tag;
import pl.michalskrzypek.LearningPlatform.exceptions.TagNotFoundException;
import pl.michalskrzypek.LearningPlatform.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllByNames(List<String> tagsNames) {
        List<Tag> tags = new ArrayList<>(tagsNames.size());
        tagsNames
                .forEach(tagName -> {
                    Tag tag = getByName(tagName);
                    tags.add(tag);
                });
        return tags;
    }

    public Tag getByName(String tagName) {
        String tagNameLowerCase = Optional.ofNullable(tagName)
                .map(t -> t.toLowerCase())
                .filter(t -> tagRepository.findByName(t).isPresent())
                .orElseThrow(() -> new TagNotFoundException(tagName));

        return tagRepository.findByName(tagNameLowerCase).get();
    }

    public void saveNewTags(List<String> tagsNames) {
        tagsNames.stream()
                .forEach(tagName -> saveNewTag(tagName));
    }

    public void saveNewTag(String tagName) {
        Optional.of(tagName)
                .map(t -> t.toLowerCase())
                .filter(t -> !tagRepository.findByName(t).isPresent())
                .ifPresent(t -> {
                    Tag tag = new Tag();
                    tag.setName(t);
                    tagRepository.save(tag);
                });
    }

    public void increaseCount(List<Tag> tags) {
        Optional.ofNullable(tags)
            .ifPresent(tagsList -> tagsList.stream()
                                    .forEach(t -> increaseCount(t)));
    }

    public void increaseCount(Tag tag) {
        int newCount = tag.getCount() + 1;
        tag.setCount(newCount);
        tagRepository.save(tag);
    }

    public void decreaseCount(List<Tag> tags) {
        Optional.ofNullable(tags)
                .ifPresent(tagsList -> tagsList
                        .forEach(t -> decreaseCount(t)));
    }

    public void decreaseCount(Tag tag) {
        int newCount = tag.getCount() - 1;
        tag.setCount(newCount);
        tagRepository.save(tag);
    }
}
