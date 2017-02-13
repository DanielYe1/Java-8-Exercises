package caelumColletions;

import org.hamcrest.CoreMatchers;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daniel.ye on 10/02/17.
 */
public class ContaPoupancaTest {
    @Test
    public void deveria_ordenar_lista_com_contas() {
        List<ContaPoupanca> contas = new ArrayList<>();
        List<ContaPoupanca> ans = new ArrayList<>();

        ContaPoupanca c1 = new ContaPoupanca(1973);
        contas.add(c1);

        ContaPoupanca c2 = new ContaPoupanca(1462);
        contas.add(c2);

        ContaPoupanca c3 = new ContaPoupanca(1854);
        contas.add(c3);
        ans.add(c2);
        ans.add(c3);
        boolean add = ans.add(c1);
        Collections.sort(contas);
        assertThat(contas, equalTo(ans));
    }

}