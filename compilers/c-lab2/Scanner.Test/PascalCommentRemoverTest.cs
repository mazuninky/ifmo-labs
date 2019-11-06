using NUnit.Framework;
using Scanner;
using Scanner.lexems;

namespace Tests
{
    public class PascalCommentRemoverTest
    {
        private const string testComments = "lol{dsas \n dsasd }kek";
        private const string testSpaces = "lol     kek";
        private const string testNewLine = "lol\n\nkek";

        private PascalScannerPreparer _scannerPreparer;

        [SetUp]
        public void Setup()
        {
            _scannerPreparer = new PascalScannerPreparer();
        }

        [Test]
        public void TestComments()
        {
            var result = _scannerPreparer.Prepare(testComments);
            Assert.AreEqual(result, "lolkek");
        }
        
        [Test]
        public void TestSpaces()
        {
            var result = _scannerPreparer.Prepare(testSpaces);
            Assert.AreEqual(result, "lol kek");
        }
        
        [Test]
        public void TestNewLine()
        {
            var result = _scannerPreparer.Prepare(testNewLine);
            Assert.AreEqual(result, "lolkek");
        }
    }
}