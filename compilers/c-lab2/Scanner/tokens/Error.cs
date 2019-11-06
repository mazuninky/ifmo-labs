using System;

namespace Scanner.tokens
{
    public class Error : Token
    {
        public string Value { get; }

        public Error(char value) : base(TokenType.ERROR)
        {
            Value = value.ToString();
        }

        public Error(string value) : base(TokenType.ERROR)
        {
            Value = value;
        }
    }
}