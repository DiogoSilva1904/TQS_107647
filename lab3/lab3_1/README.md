## A)

1. Verifica se o employee encontrado na base de dados, dado o nome, corresponde ao employee correto.

```java
    @Test
    void whenFindAlexByName_thenReturnAlexEmployee() {
        Employee alex = new Employee("alex", "alex@deti.com");
        entityManager.persistAndFlush(alex);
        Employee found = employeeRepository.findByName(alex.getName());
        assertThat(found).isEqualTo(alex);
    }
```

2. Verifica se o employee encontrado na base de dados é nulo(não existe).

```java
@Test
    void whenInvalidEmployeeName_thenReturnNull() {
        Employee fromDb = employeeRepository.findByName("Does Not Exist");
        assertThat(fromDb).isNull();
    }
```    


3. Verifica se existe algum employee na base de dados com o nome "john".

```java
@Test
     void whenValidName_thenEmployeeShouldExist() {
        boolean doesEmployeeExist = employeeService.exists("john");
        assertThat(doesEmployeeExist).isTrue();

        verifyFindByNameIsCalledOnce("john");
    }
```


## B)

No exemplo B é feito mock do repositório(é o repositório que vai buscar os dados à BD e, por isso, não se usa uma base de dados) de nome EmployeeRepository.Este mock é depois injetado nos serviços, EmployeeServiceImpl.

```java
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService; 
```

## C)
    
    @Mock -> cria um mock de uma interface/classe/objeto, que permite simular o seu comportamento sem ser implementado
    
    @MockBean -> cria um mock de componentes do springboot como o repositório,controller,serviço e permite simular o seu comportamento sem ser implementado. Isto é, subtitui-se um bean real por um mock, apenas para os testes pois, o resto da aplicação continuara a funcionar como o Spring Boot funciona normalmente.

## D)  

application-integrationtest.properties vai ser usado quando forem corridos os testes de integração.É útil pois pode ser usado para especificar uma base de dados que pode ser utilizada para usar nos testes.

```java
    spring.datasource.url=jdbc:mysql://localhost:33060/tqsdemo
    spring.jpa.hibernate.ddl-auto=create-drop -> sempre que se correm os testes de integração a base de dados é eliminada e criada.
    spring.datasource.username=demo
    spring.datasource.password=demo
```    

## E)

   C->  usamos a anotação @WebMvcTest para criar uma ambiente de teste simplificado onde é injetado um objeto MockMvc

   D->  é criado um ambiente de teste com @SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = EmployeeMngrApplication.class) em que não é usado a API

   E->  é carregado todo o contexto do SpringBoot usando @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT).Difere do D pois no E é usado um cliente de API completo.
