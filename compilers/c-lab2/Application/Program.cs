using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Scanner;
using Scanner.tokens;

namespace Application
{
    class Program
    {
        static void Main(string[] args)
        {
            IScannerPreparer preparer = new MultilineScannerPreparer();

            var text = File.ReadAllText("example2");
            text = preparer.Prepare(text);

            var lines = text.Split(new[] {Environment.NewLine}, StringSplitOptions.RemoveEmptyEntries);
            lines = lines.Select(it => it + "\n").ToArray();

            var tokens = new List<Token>();
            var table = new Table();

            Console.WriteLine("Tokens table");

            var lineNumber = 0;
            List<Token> lineTokens;
            var stringify = new TokenStringify(table);
            foreach (var line in lines)
            {
                lineTokens = Scanner.Scanner.Scan(line, table);
                tokens.AddRange(lineTokens);

                lineTokens.ForEach(it => { Console.WriteLine(stringify.Stringify(it, lineNumber)); });

                lineNumber++;
            }
        }
    }
}