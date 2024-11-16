package com.example.goit_academy_dev_hw14.service;

import com.example.goit_academy_dev_hw14.model.Note;
import com.example.goit_academy_dev_hw14.util.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    public NoteService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Optional<Note> getById(long id) {
        return Optional.ofNullable(notes.get(id));
    }

    public Note create(Note note) {
        long id = idGenerator.generateId();
        Note newNote = Note.builder()
                .id(id)
                .title(note.getTitle())
                .content(note.getContent())
                .build();
        notes.put(id, newNote);
        return newNote;
    }

    public Note update(Note note) {
        return Optional.ofNullable(notes.get(note.getId()))
                .map(existingNote -> {
                    Note updatedNote = Note.builder()
                            .id(note.getId())
                            .title(note.getTitle())
                            .content(note.getContent())
                            .build();
                    notes.put(note.getId(), updatedNote);
                    return updatedNote;
                })
                .orElseThrow(() -> new NoSuchElementException("Note with id " + note.getId() + " not found"));
    }

    public void delete(long id) {
        if (notes.remove(id) == null) {
            throw new NoSuchElementException("Note with id " + id + " not found");
        }
    }
}
