using System;
using System.Collections.Generic;
using Scanner;

namespace Syntax
{
    public class SyntaxAnalizeTable
    {
        private Dictionary<KeyValuePair<string, Token>, List<string>> table;

        public SyntaxAnalizeTable(Dictionary<KeyValuePair<string, Token>, List<string>> table)
        {
            this.table = table;
        }

        public List<string> this[string nonTerminal, Token terminal]
        {
            get => table[new KeyValuePair<string, Token>(nonTerminal, terminal)];
            set => table[new KeyValuePair<string, Token>(nonTerminal, terminal)] = value;
        }
    }
}