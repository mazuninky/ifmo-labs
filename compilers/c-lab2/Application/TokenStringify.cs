using System;
using Scanner;
using Scanner.tokens;

namespace Application
{
    public class TokenStringify
    {
        private Table _table;

        public TokenStringify(Table table)
        {
            _table = table;
        }

        public string Stringify(Token token, int line)
        {
            var type = StringifyType(token.Tag);
            var value = StringifyValue(token);
            if (value.Length == 0)
            {
                return type + " | " + line;
            }
            else
            {
                return type + " | " + value + " | " + line;
            }
        }

        private string StringifyValue(Token token)
        {
            switch (token.Tag)
            {
                case TokenType.NUM:
                    var num = token as Num;
                    return num.Value.ToString();

                case TokenType.ID:
                    var id = token as Id;
                    return _table.Get(id.Index).name;

                case TokenType.OP:
                    var op = token as Operation;
                    return op.OperationType.ToString();

                case TokenType.ERROR:
                    var error = token as Error;
                    return error.Value;

                default:
                    return "";
            }
        }

        private string StringifyType(TokenType tokenType)
        {
            switch (tokenType)
            {
                case TokenType.NUM:
                    return "Number";
                case TokenType.ID:
                    return "Id";
                case TokenType.OP:
                    return "Operator";
                case TokenType.COMMA:
                    return "Comma";
                case TokenType.DOT:
                    return "Dot";
                case TokenType.VAR:
                    return "Var";
                case TokenType.BEGIN:
                    return "Begin";
                case TokenType.END:
                    return "End";
                case TokenType.IF:
                    return "If";
                case TokenType.ELSE:
                    return "Else";
                case TokenType.LP:
                    return "Open parenthesis";
                case TokenType.RP:
                    return "Close parenthesis";
                case TokenType.ERROR:
                    return "Error";
                default:
                    return "Unknown";
            }
        }
    }
}