package com.alexeyrand.justsymbols.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SymbolsService {
    public String solve(String str) {
        Map<Character, Integer> symsMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Integer count = symsMap.get(str.charAt(i));
            if (count == null) {
                symsMap.put(str.charAt(i), 1);
            } else {
                symsMap.put(str.charAt(i), ++count);
            }
        }

        List<Map.Entry<Character, Integer>> ppp = symsMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue()
                        .reversed()).toList();

        StringBuilder sb = new StringBuilder("{");

        for (Map.Entry<Character, Integer> e : ppp) {
            sb.append("\"" + e.getKey() + "\"")
                    .append(": ")
                    .append(e.getValue())
                    .append((", "));
        }


        return sb.substring(0, sb.length() - 2) + "}";
    }
}
