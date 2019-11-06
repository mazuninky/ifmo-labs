using System;
using System.Runtime.Serialization.Json;

namespace Scanner
{
    public class Token
    {
        public TokenType Tag { get; }

        public Token(TokenType tag)
        {
            Tag = tag;
        }
    }
}