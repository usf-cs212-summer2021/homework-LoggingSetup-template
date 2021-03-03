import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Runs a couple of tests to make sure Log4j2 is setup.
 *
 * NOTE: There are better ways to test log configuration---we will keep it
 * simple here because we just want to make sure you can run and configure
 * Log4j2.
 *
 * This is also not the most informative configuration---it is just one of the
 * most testable ones that require you to learn about how to handle stack trace
 * output.
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2021
 */
@TestMethodOrder(MethodName.class)
public class LoggingSetupTest {

	/** Used to capture console output. */
	private static ByteArrayOutputStream capture = null;

	/**
	 * Setup that runs before each test.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@BeforeAll
	public static void setup() throws IOException {
		// delete any old debug files
		Files.deleteIfExists(Path.of("debug.log"));

		// capture all system console output
		PrintStream original = System.out;
		capture = new ByteArrayOutputStream();
		System.setOut(new PrintStream(capture));

		// run main() only ONCE to avoid duplicate entries
		// and shutdown log manager to flush the debug files
		LoggingSetup.main(null);
		LogManager.shutdown();

		// restore system.out
		System.setOut(original);
		System.out.println(capture.toString());
	}

	/**
	 * Tests Root logger console output.
	 */
	@Nested
	public class A_RootConsoleTests {
		/**
		 * Captures the console output and compares to expected.
		 * 
		 * @param line the line number of console output to test
		 * @param expected the expected output for that line of console output
		 * @throws IOException if an I/O error occurs
		 */
		@ParameterizedTest
		@CsvSource({ "1,wren", "2,ERROR eagle", "3,Catching finch"})
		public void testConsole(int line, String expected) throws IOException {
			String[] captured = capture.toString().split("[\\n\\r]+");
			
			if (line >= captured.length) {
				assertEquals(expected, "", "Did not produce enough console output.");
			}
			
			assertEquals(expected, captured[line].strip());
		}
	}

	/**
	 * Tests LoggerSetup logger console output.
	 */
	@Nested
	public class B_LoggerConsoleTests {
		/**
		 * Captures the console output and compares to expected.
		 * 
		 * @param line the line number of console output to test
		 * @param expected the expected output for that line of console output
		 * @throws IOException if an I/O error occurs
		 */
		@ParameterizedTest
		@CsvSource({ "5,ibis", "6,wren", "7,ERROR eagle", "8,Catching finch" })
		public void testConsole(int line, String expected) throws IOException {
			String[] captured = capture.toString().split("[\\n\\r]+");
			
			if (line >= captured.length) {
				assertEquals(expected, "", "Did not produce enough console output.");
			}
			
			assertEquals(expected, captured[line].strip());
		}
	}

	/**
	 * Tests first part of file output.
	 */
	@Nested
	public class C_FileNormalTests {
		/**
		 * Open the debug.log file as a list and compare to expected.
		 *
		 * @throws IOException if an I/O error occurs
		 */
		@Test
		public void testFile() throws IOException {
			// test file output is as expected
			List<String> expected = Files.readAllLines(Path.of("src", "test", "resources", "debug.log"));
			List<String> actual = Files.readAllLines(Path.of("debug.log"));

			// only test a subset here
			expected = expected.subList(0, 4);
			actual = actual.subList(0, 4);

			assertEquals(expected, actual, "Compare debug.log and test/debug.log in Eclipse.");
		}
	}

	/**
	 * Tests first part of file output.
	 */
	@Nested
	public class D_FileExceptionTests {
		/**
		 * Open the debug.log file as a list and compare to expected.
		 *
		 * @throws IOException if an I/O error occurs
		 */
		@Test
		public void testFile() throws IOException {
			// test file output is as expected
			List<String> expected = Files.readAllLines(Path.of("src", "test", "resources", "debug.log"));
			List<String> actual = Files.readAllLines(Path.of("debug.log"));

			// only test a subset here
			expected = expected.subList(4, expected.size());
			actual = actual.subList(4, actual.size());

			assertEquals(expected, actual, "Compare debug.log and test/debug.log in Eclipse.");
		}
	}
}
