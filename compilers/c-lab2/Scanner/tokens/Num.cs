namespace Scanner.tokens
{
    public class Num : Token
    {
        public int Value { get; }
        public Num(int value) : base(TokenType.NUM)
        {
            
        }
    }
}