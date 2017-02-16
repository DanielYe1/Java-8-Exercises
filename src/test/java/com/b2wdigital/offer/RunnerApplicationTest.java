package com.b2wdigital.offer;

import com.b2wdigital.offer.controller.BasketController;
import com.b2wdigital.offer.service.Messenger;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by daniel.ye on 16/02/17.
 */
public class RunnerApplicationTest {

    @Test
    public void deveria_fechar_o_programa() {
        BasketController controller = mock(BasketController.class);
        Messenger messenger = mock(Messenger.class);

        RunnerApplication runner = new RunnerApplication();

        when(messenger.ask(eq(""))).thenReturn("5");

        runner.run(messenger, controller);


        verify(messenger, times(7)).send(anyString());
        verify(messenger, times(1)).ask(eq(""));
    }


}