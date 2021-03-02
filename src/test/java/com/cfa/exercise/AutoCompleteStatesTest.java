package com.cfa.exercise;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class AutoCompleteStatesTest {
    private AutoCompleteStates underTest = new AutoCompleteStates();

    private String input = "NE";  // Searching by this text should product the results below in order

    private List<String> expect = Arrays.asList("Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",  "Connecticut", "Maine", "Minnesota", "Tennessee");
    private List<String> result;

    @Before
    public void getResults(){
        result = underTest.filterStates(input);
    }

    // Edge Cases
    @Test
    public void it_should_return_an_empty_list_when_input_null(){
        expectEmptyResult(null);
    }

    @Test
    public void it_should_return_an_empty_list_when_input_blank(){
        expectEmptyResult("");
    }

    @Test
    public void it_should_return_an_empty_list_when_no_match(){
        expectEmptyResult("ZZ");
    }

    private void expectEmptyResult(String input){
        List<String> result = underTest.filterStates(input);
        assertEquals("No state should match " + input, 0, result.size());
    }
    // End Edge Cases

    @Test
    public void it_should_return_the_state_that_matches_the_abbreviation(){
        assertThat(result, hasItem("Nebraska"));
    }

    @Test
    public void it_should_return_states_that_start_with_the_input_text(){
        assertThat(result, hasItems("Nebraska", "New Hampshire", "New Jersey", "New Mexico", "New York", "Nevada"));
    }

    @Test
    public void it_should_return_states_that_contain_the_input_text(){
        assertThat(result, hasItems("Tennessee", "Nebraska", "New Hampshire", "New Jersey", "New Mexico", "New York", "Nevada", "Minnesota", "Maine", "Connecticut"));
    }

    @Test
    public void it_should_not_return_duplicates(){
        Set<String> setOfResults = new HashSet<>(result);
        assertThat(result, hasSize(setOfResults.size()));
    }

    @Test
    public void the_first_result_should_be_the_abbreviation_match(){
        String firstResult = result.get(0);
        assertThat(firstResult, is("Nebraska"));
    }

    @Test
    public void the_next_results_should_start_with_the_input_text(){
        if(result.size() < 6)
            fail();
        for(int i=1; i<6; i++){
            String shouldStartWith = result.get(i);
            assertThat(shouldStartWith.toUpperCase(), startsWith(input));
        }
    }

    @Test
    public void it_should_not_return_states_that_do_not_match(){
        List<String> allStates = Arrays.stream(State.values()).map(State::getStateName).collect(Collectors.toList());
        List<String> nonMatches = allStates.stream().filter(state -> !expect.contains(state)).collect(Collectors.toList());
        assertFalse(nonMatches.stream().anyMatch(state -> result.contains(state)));
    }

    @Test  // All tests should pass if this on passes
    public void it_should_contain_the_right_matches_in_the_right_order(){
        assertThat(result, contains("Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",  "Connecticut", "Maine", "Minnesota", "Tennessee"));
    }


}