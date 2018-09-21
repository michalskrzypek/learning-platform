package pl.michalskrzypek.LearningPlatform.services;

import org.springframework.stereotype.Service;
import pl.michalskrzypek.LearningPlatform.entities.Tag;
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

    public List<Tag> getAllByNames(List<String> tagsNames){
        saveNewTags(tagsNames);
        List<Tag> tags = new ArrayList<>(tagsNames.size());
        tagsNames.stream()
                .map(tagName -> tagName.toLowerCase())
                .forEach(tagName -> {
                    Tag tag = tagRepository.findByName(tagName).get();
                    tags.add(tag);
                });
        return tags;
    }

    public void saveNewTags(List<String> tagsNames) {
        tagsNames.stream()
                .map(tagName -> tagName.toLowerCase())
                .filter(tagName -> !tagRepository.findByName(tagName).isPresent())
                .forEach(tagName -> {
                    Tag tag = new Tag();
                    tag.setName(tagName);
                    tagRepository.save(tag);
                });
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

    public Tag getByName(String tagName){
        return tagRepository.findByName(tagName).get();
    }

    public void addCount(List<Tag> tags){
        tags.stream()
                .forEach(t -> {
                    addCount(t);
                });
    }

    private void addCount(Tag tag){
        int newCount = tag.getCount() + 1;
        tag.setCount(newCount);
        tagRepository.save(tag);
    }
}
