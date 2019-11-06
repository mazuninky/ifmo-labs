using NUnit.Framework;
using Scanner;
using Scanner.lexems;

namespace Tests
{
    public class ScannerTest
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void TokenizerTest()
        {
            var someBODYONCETOLDMETokenizerString = "a = (b + c;";
            var table = new Table();
            var tokens = Scanner.Scanner.Scan(someBODYONCETOLDMETokenizerString, table);
            Assert.Pass("Tokens", tokens);
        }
    }
}