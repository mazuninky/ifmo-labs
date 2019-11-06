namespace Scanner.tokens
{
    public class Operation : Token
    {
        public Operations OperationType;

        public Operation(Operations type) : base(TokenType.OP)
        {
            OperationType = type;
            // tokens.Add(new new BinaryOp(BinaryOperations.PLUS));
            // tokens.Add(BinaryOp.Plus);
        }

        public enum Operations
        {
            PLUS,
            MINUS,
            MUL,
            DIV,
            GREATER,
            LESS,
            EQ,
            BSL, //binary shift left
            BSR, //binary shift right
            ASSIGN
        }

        public static Operation Plus = new Operation(Operations.PLUS);
        public static Operation Minus = new Operation(Operations.MINUS);
        public static Operation Mul = new Operation(Operations.MUL);
        public static Operation Div = new Operation(Operations.DIV);
        public static Operation Greater = new Operation(Operations.GREATER);
        public static Operation Less = new Operation(Operations.LESS);
        public static Operation Eq = new Operation(Operations.EQ);
        public static Operation Bsl = new Operation(Operations.BSL);
        public static Operation Bsr = new Operation(Operations.BSR);
        public static Operation Assign = new Operation(Operations.ASSIGN);
    }
}