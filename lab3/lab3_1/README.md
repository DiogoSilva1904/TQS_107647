a)

assertThat(found).isEqualTo(alex);  A_EmployeeRepositoryTest.java -> line 37

assertThat(fromDb).isNull();  A_EmployeeRepositoryTest.java -> line 38

O uso da biblioteca de asserts, AssertJ é verificada pelo uso de assertThat.

b)

Na situação B é feito mock do repositório, e sendo assim não é usada nenhuma BD pois é o repositório que vai buscar os dados à base de dados.   
Como não é suposto implementar os serviços(que dão uso do repositorio) e os testes ao mesmo tempo, não é necessário ter uma base de dados para testar os serviços.
    
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService; 
c)
    @Mock -> cria um mock de uma interface/classe/objeto, que permite simular o seu comportamento sem ser implementado
    @MockBean -> cria um mock de componentes do springboot como o repositório,controller,serviço e permite simular o seu comportamento sem ser implementado. Isto é, subtitui-se um bean real por um mock, apenas para os testes pois, o resto da aplicação continuara a funcionar como o Spring Boot funciona normalmente.