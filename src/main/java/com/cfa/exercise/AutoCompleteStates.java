package com.cfa.exercise;

import java.util.*;


class AutoCompleteStates {

    /*
        For the given text, return the names of the States that are considered matches.
        There should be no duplicates in the collection.
      A State is a match if it meets one of the following conditions.  Results should be returned
      in the priority listed below where one is highest priority and three is lowest.  If two matches
      are the same priority, those matches should be sorted alphabetically.
      1. Match exact abbreviation (Should be case insensitive)
      2. Match start of state name
      3. Match any part of state name
    */

    public List<String> filterStates(String textEntered) {
        List<String> results = new ArrayList();
        if ( null == textEntered || "".equals(textEntered.trim())){
            return results;
        }

        Map<Integer, Map<String, State>> orderedState = new TreeMap();

        String caseInsInput = textEntered.toLowerCase();

        //time complexity = O(n)
        Arrays.stream(State.values())
                .filter(e -> e.getStateName().toLowerCase().contains(caseInsInput) || e.name().toLowerCase().equals(caseInsInput))
                .forEach(e -> {
                    if (e.name().toLowerCase().equals(caseInsInput)) {
                        Map<String, State> main = new TreeMap();
                        main.put(e.getStateName(), e);
                        orderedState.put(-1, main);
                    } else {
                        int mainIdx = e.getStateName().toLowerCase().indexOf(caseInsInput);
                        if (!orderedState.containsKey(mainIdx)){
                            orderedState.put(mainIdx, new TreeMap<>());
                        }
                        orderedState.get(mainIdx).put(e.getStateName(), e);
                    }
                });

        for (Map.Entry<Integer, Map<String, State>> entry : orderedState.entrySet()) {
            results.addAll(entry.getValue().keySet());
        }

        return results;
    }

}
