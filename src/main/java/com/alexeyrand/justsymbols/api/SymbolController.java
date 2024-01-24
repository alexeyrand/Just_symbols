package com.alexeyrand.justsymbols.api;

import com.alexeyrand.justsymbols.service.SymbolsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SymbolController {

    private final SymbolsService symbolsService;

    private final static String GET_SYMBOLS = "/symbols" ;
    private final static int MAX_SYMBOLS = 30;

    @GetMapping(GET_SYMBOLS)
    public String getSymbols(@RequestParam String str) {

        if (str.toLowerCase().isBlank()) {
            throw new BadRequestException("Строка не может быт пустой");
        }

        if (str.length() > MAX_SYMBOLS) {
            throw new BadRequestException("Строка превышает максимальную длину. Введите строку длиной " + MAX_SYMBOLS +".");
        }

        if (str.contains(" ")) {
            throw new BadRequestException("В строке не должно быть пробелов");
        }
        System.out.println(str);
        System.out.println(str.toLowerCase());
        String ans = symbolsService.solve(str.toLowerCase());
        System.out.println(ans);
        return ans;
    }

}
