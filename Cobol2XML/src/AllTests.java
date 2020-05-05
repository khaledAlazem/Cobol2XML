import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CobolTests.class,DisplayTests.class, TestDivision.class ,
					AcceptTests.class , PerformTests.class ,AssemblyTests.class,
					  AssemblerTests.class,TokenizerTests.class , VariableTests.class, CommentTests.class })
public class AllTests {

}
