package com.alexeyrand.justsymbols.api;

import com.alexeyrand.justsymbols.service.SymbolsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SymbolControllerTest {

    private final SymbolsService service = new SymbolsService();

    private final SymbolController controller = new SymbolController(service);

    @Test
    public void testExceptionOnEmpty() {
        boolean exceptionThrown = false;
        try {
            controller.getSymbols("   ");
        } catch (BadRequestException BRE) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testExceptionOnMaxLen() {
        boolean exceptionThrown = false;
        try {
            controller.getSymbols("aaaaddddqqqqeeeeeeeerrrrrrrttttttttttyyyyyyyyyyyuuuuuuuuuuuiiiiiiiii");
        } catch (BadRequestException BRE) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void testExceptionOnSpace() {
        boolean exceptionThrown = false;
        try {
            controller.getSymbols("aaaa ddd");
        } catch (BadRequestException BRE) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void result_1() {
        String res = service.solve("aaaabbc");
        assertEquals("{\"a\": 4, \"b\": 2, \"c\": 1}", res);
    }

    @Test
    public void result_2() {
        String res = service.solve("QQqqqwwrrrrrr   ");
        assertEquals("{\"r\": 6, \"q\": 5, \"w\": 2}", res);
    }


    @Test
    public void result_3() {
        String res = service.solve("   aaaBbBcCc   ");
        assertEquals("{\"a\": 3, \"b\": 3, \"c\": 3}", res);
    }

}
