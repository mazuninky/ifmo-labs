using NUnit.Framework;
using Scanner;
using Scanner.lexems;

namespace Tests
{
    public class MultilineCommentRemoverTest
    {
        private const string toTestComment = "kek/*dsas \n dsasd */lol";
        private const string toTestSpaces = "kek         lol";
        private const string toTestNewLine = "keklol\n\nfefe";


        private MultilineScannerPreparer _scannerPreparer;

        [SetUp]
        public void Setup()
        {
            _scannerPreparer = new MultilineScannerPreparer();
        }

        [Test]
        public void TestComments()
        {
            Assert.AreEqual(_scannerPreparer.Prepare(toTestComment), "keklol");
        }

        [Test]
        public void TestSpaces()
        {
            Assert.AreEqual(_scannerPreparer.Prepare(toTestSpaces), "kek lol");
        }

        [Test]
        public void TestNewLine()
        {
            Assert.AreEqual(_scannerPreparer.Prepare(toTestNewLine), "keklolfefe");
        }
    }
}