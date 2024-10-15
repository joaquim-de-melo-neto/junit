import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class ConditionalTestExample {

    @Test
    void conditionalTest(TestInfo testInfo) {
        Assumptions.assumeTrue(testInfo.getDisplayName().startsWith("Fast"),
                () -> "Este teste é lento e será executado apenas em builds noturnos");

        // ... Implementação do teste ...
    }
}```

/* Neste exemplo, o teste só será executado se o nome do teste começar com "Fast". Isso permite que você controle quais testes são executados com base em critérios como tempo de execução ou ambiente.

**Outros Usos Comuns:**

* **Personalizar mensagens de log:** Adicionar informações do teste às mensagens de log para facilitar a depuração.
* **Integrar com ferramentas externas:** Enviar informações sobre os testes para ferramentas de análise de cobertura de código ou de rastreamento de bugs.
* **Criar testes parametrizados dinâmicos:** Gerar testes parametrizados com base em dados externos.

**Informações Disponíveis em `TestInfo`:**

* `getDisplayName()`: Retorna o nome do teste.
* `getTestClass()`: Retorna informações sobre a classe de teste.
* `getTestMethod()`: Retorna informações sobre o método de teste.
* `getTags()`: Retorna as tags associadas ao teste.
* `getDisplayNameGeneration()`: Retorna informações sobre a geração de nomes de testes.

**Conclusão:**

O `TestInfo` é uma ferramenta versátil que pode ser usada para personalizar e controlar seus testes JUnit 5. Ao aproveitar as informações fornecidas pelo `TestInfo`, você pode criar testes mais robustos, informativos e adaptáveis.

**Quer explorar mais algum exemplo específico ou tem alguma dúvida sobre o uso do `TestInfo`?**/
