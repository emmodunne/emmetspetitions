package com.controller;

import com.model.Petition;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@WebMvcTest(PetitionController.class)
@AutoConfigureMockMvc
public class PetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PetitionController petitionController;

    @Mock
    private List<Petition> petitions;

    @Test
    public void testCreatePetition() throws Exception {
        // Given
        String petitionTitle = "Test Petition";
        Petition petition = new Petition();
        petition.setTitle(petitionTitle);

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/create-petition")
                .flashAttr("petition", petition))

                // Then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/view-petition/" + petition.getId()));
    }

    @Test
    public void testShowSinglePetitionPage() throws Exception {
        // Given
        mockMvc = MockMvcBuilders.standaloneSetup(petitionController).build();
        Petition petition1 = new Petition("Example Petition 1", "Example Petition 1");
        Petition petition2 = new Petition("Petition 2", "Petition 2");

        // Mock
        when(petitions.stream()).thenReturn(Stream.of(petition1, petition2));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/view-petition/" + petition1.getId()))

                // Then
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("view-petition"))
                .andExpect(MockMvcResultMatchers.model().attribute("petition", petition1));
    }

    @Test
    public void testSearchPetitions() throws Exception {
        // Given
        mockMvc = MockMvcBuilders.standaloneSetup(petitionController).build();
        String searchKeyword = "example";
        Petition petition1 = new Petition("Example Petition 1", "Example Petition 1");
        Petition petition2 = new Petition("Petition 2", "Petition 2");

        // Mock behavior
        when(petitions.stream()).thenReturn(Stream.of(petition1, petition2));

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/search-petitions")
                .param("search", searchKeyword))

                // Then
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("result-search-petition"))
                .andExpect(MockMvcResultMatchers.model().attribute("petitions", List.of(petition1)));

    }

}
