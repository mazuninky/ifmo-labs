namespace Scanner.tokens
{
    public class Id : Token
    {
        public int Index { get; }

        public Id(int index) : base(TokenType.ID)
        {
            Index = index;
        }
    }
}