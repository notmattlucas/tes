package com.tes.db;

import com.tes.api.TemplateSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tes.utils.DbUtils.createAndSave;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTemplateRepoTest {

    private Repository<TemplateSpecification> repo = InMemoryTemplateRepository.INSTANCE;

    @BeforeEach
    public void setup() {
        InMemoryTemplateRepository.INSTANCE.drop();
    }

    @Test
    public void shouldSaveAndLoadTemplate() {
        TemplateSpecification template = createAndSave();
        Optional<TemplateSpecification> reload = repo.findById(template.getId());
        assertThat(reload.get()).isEqualTo(template);
    }

    @Test
    public void shouldDeleteTemplate() {
        TemplateSpecification template = createAndSave();
        repo.delete(template.getId());
        Optional<TemplateSpecification> reload = repo.findById(template.getId());
        assertThat(reload.isPresent()).isFalse();
    }

    @Test
    public void shouldPaginateAcrossAll() {
        IntStream.range(0, 100).forEach((i) -> createAndSave());
        List<List<TemplateSpecification>> pages = IntStream.range(0, 10)
                .mapToObj((i) -> repo.find(i * 10, 10))
                .collect(Collectors.toList());
        assertThat(pages.size()).isEqualTo(10);
        for (List<TemplateSpecification> page: pages) {
            assertThat(page.size()).isEqualTo(10);
        }
    }

    @Test
    public void shouldReturnCountOfRecords() {
        IntStream.range(0, 100).forEach((i) -> createAndSave());
        assertThat(repo.count()).isEqualTo(100);
    }

}
