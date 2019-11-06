using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using Scanner.lexems;
using Scanner.tokens;

namespace Scanner
{
    public static class Scanner
    {
        /*____________¶¶
 ___________¶¶¶¶
 __________¶¶¶¶¶¶
 _________¶¶¥¥¥¶¶¶
 ________¶¶¥¥¥¥¥¶¶¶__________________________________________¶¶¶¶¶¶¶¶
 ________¶¶¥¥¥¥¥¥¶¶¶_____________________________________¶¶¶¶¶¥¥¥¥¥¶¶
 ________¶¶¥¥¥¥¥¥ƒƒ¶¶________________________________¶¶¶¶¥¥¥¥¥¥¥¥¶¶¶¶
 ________¶¶¥¥¥¥ƒƒƒƒƒ¶¶___________________________¶¶¶¶ƒƒ¥¥¥¥¥¥¥¥¶¶¶¶
 ________¶¶¶ƒƒƒƒƒƒƒƒ§¶¶________________________¶¶ƒƒƒƒƒƒƒ¥¥¥¥¥¶¶¶¶
 _________¶¶¶ƒƒƒƒƒƒ§§¶¶____________________¶¶¶¶ƒƒƒƒƒƒƒƒƒƒ¥¥¶¶¶¶
 ___________¶¶ƒƒƒƒƒ§§¶¶__________________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¶¶¶¶
 ____________¶¶ƒƒ§§§§¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§¶¶
 _____________¶¶§§§§§§§ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§¶¶
 ______________¶¶§§§ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§¶¶¶¶___________________¶¶¶¶¶¶
 ____________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§¶¶¶____________________¶¶¶ƒƒƒƒƒ¶¶
 __________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¶¶¶¶¶¶ƒƒƒƒ§§§¶¶¶___________________¶¶ƒƒƒƒƒƒƒƒ¶¶
 _________¶¶ƒƒ¶¶¶¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¶¶__¶¶¶¶ƒƒƒ§§§§§¶¶________________¶¶ƒƒƒƒƒƒƒƒƒƒ¶¶
 ________¶¶ƒƒ¶¶__¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¶¶¶¶¶¶¶¶ƒƒƒ§§§§§§¶¶___________¶¶¶¶ƒƒƒƒƒƒƒƒ§§§§§§¶¶
 _______¶¶ƒƒƒ¶¶¶¶¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¶¶¶¶¶¶ƒƒƒƒƒ§§§§§§¶¶________¶¶ƒƒƒƒƒƒƒƒ§§§§§§§§§§¶¶
 _______¶¶ƒƒƒƒ¶¶¶¶ƒƒƒƒƒ¥¥¥ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ####§§§§§¶¶____¶¶¶¶ƒƒƒƒƒƒƒƒ§§§§§§§§§§§§¶¶
 _______¶¶###ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¥¥ƒƒƒƒƒƒ########§§§§¶¶¶¶¶¶ƒƒƒƒƒƒƒƒ§§§§§§§§§§§§§§§§¶¶
 _______¶¶####ƒƒƒƒƒƒƒƒ¥¥¥¥¥¥¥¥¥¥¥ƒƒƒƒƒƒ########§§¶¶¶¶ƒƒ¶¶¶¶ƒƒƒƒ§§§§§§§§§§§§§§§§§§¶¶
 ____¶¶¶¶¶¶###ƒƒƒƒƒƒƒƒƒ¥¥¥#####¥ƒƒƒƒƒƒƒ########¶¶ƒƒ¶¶ƒƒƒƒƒƒ¶¶§§§§§§§§§§§§§§§§§§§§¶¶
 __¶¶¶ƒƒ¶¶¶¶#ƒƒƒƒƒƒƒƒƒƒ¥¥####¥¥ƒƒƒƒƒƒƒƒƒƒ####§§¶¶ƒƒƒƒƒƒƒƒ¶¶¶¶§§§§§§§§§§§§§§§§¶¶¶¶
 _¶¶ƒƒ¶ƒƒƒƒ¶¶ƒƒƒƒƒƒƒƒƒƒƒƒ¥¥¥¥ƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§¶¶ƒƒƒƒƒƒƒƒƒƒƒƒ¶¶§§§§§§§§§§§§¶¶¶¶
 ¶¶ƒƒƒƒƒƒ§§§§¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ¶¶ƒƒƒƒƒƒƒƒ§§§§¶¶§§§§§§§§§§¶¶¶¶
 __¶¶ƒƒ§§§§§§¶¶¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§¶¶¶§§§§§§§§¶¶
 ____¶¶§§§§§§§¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§¶__¶§§§§§§¶¶
 ______¶¶§§§§§§ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§¶¶____¶¶§§§§§§¶¶
 ________¶¶¶§ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§¶¶_______¶¶§§§§§§¶¶
 _________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§¶¶¶¶____¶¶¶¶§§§§§§§§§§¶¶
 _________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§¶¶§§¶¶¶¶¶¶ƒƒ§§§§§§§§¶¶¶¶
 ________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§¶¶ƒƒƒƒ§§§§§§¶¶¶¶
 ________¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§§¶¶§§§§§§§¶¶¶¶
 __¶¶¶¶¶¶¶¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§§§¶¶§§§§§§¶¶
 _¶¶ƒƒ¶¶ƒƒƒ¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§§§¶¶¶¶§§§§§§¶¶
 _¶¶ƒƒƒ¶¶ƒƒƒ¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§§§§¶¶__¶¶¶###§§§¶¶
 __¶¶§§§§§§§§¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§§§§§§§¶¶¶¶¶#######§§§¶¶
 ___¶¶§§§§§§§§¶¶ƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒƒ§§§§§§§§§§§§§§§§§########¶¶¶¶¶¶
 ____¶¶§§§§§§§§¶¶§§§§ƒƒƒƒƒƒƒ§§§§§§§§§§§§§§§§§§§####¶¶¶¶¶¶
 _____¶¶§§§§§§§¶¶§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§¶¶¶¶
 _______¶¶¶¶¶¶¶§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§¶¶
 ______________¶¶¶¶¶¶¶¶¶¶§§§§§§§§§§§§§§§§§§§§¶¶
 ________________________¶¶¶¶¶¶§§§§§§§§§§¶¶¶¶
 ____________________________¶¶¶¶§§§§¶¶¶¶¶
 ____________________________¶¶§§§§§§§§¶¶
 ____________________________¶¶§§¶¶§§§¶¶
 _____________________________¶¶§¶¶§§¶¶
 ______________________________¶¶¶¶¶¶
 ---*/
        private const int INITIAL_STATE = 0;
        private const int NUMBER_STATE = 1;
        private const int ID_STATE = 2;
        private const int SPECIAL_STATE = 3;

        public static List<Token> Scan(string input, Table table)
        {
            var tokens = new List<Token>();
            // Prepare data
            var spaceRegex = new Regex(@"\s\s+");
            input = spaceRegex.Replace(input, " ");

            var state = INITIAL_STATE;
            var stringBuilder = new StringBuilder();
            var index = 0;
            while (index != input.Length)
            {
                var symbol = input[index];
                switch (state)
                {
                    case INITIAL_STATE:
                        if (char.IsDigit(symbol))
                        {
                            state = NUMBER_STATE;
                        }
                        else if (char.IsLetter(symbol))
                        {
                            state = ID_STATE;
                        }
                        else
                        {
                            switch (symbol)
                            {
                                case '*':
                                    tokens.Add(Operation.Mul);
                                    break;
                                case '-':
                                    tokens.Add(Operation.Minus);
                                    break;
                                case '+':
                                    tokens.Add(Operation.Plus);
                                    break;
                                case '=':
                                    tokens.Add(Operation.Eq);
                                    break;
                                case '/':
                                    tokens.Add(Operation.Div);
                                    break;
                                case '(':
                                    tokens.Add(new OpenParentheses());
                                    break;
                                case ')':
                                    tokens.Add(new CloseParentheses());
                                    break;
                                case ' ':
                                case '\t':
                                case '\n':
                                    break;
                                case '.':
                                    tokens.Add(new Dot());
                                    break;
                                case ',':
                                    tokens.Add(new Comma());
                                    break;
                                case '>':
                                case '<':
                                case ':':
                                    stringBuilder.Append(symbol);
                                    state = SPECIAL_STATE;
                                    break;
                                default:
                                    tokens.Add(new Error(symbol));
                                    break;
                            }

                            index++;
                        }

                        break;

                    case SPECIAL_STATE:
                        if (!char.IsDigit(symbol) && !char.IsLetter(symbol) && symbol != ' '
                            && symbol != '\t' && symbol != '\n')
                        {
                            stringBuilder.Append(symbol);
                            index++;
                        }
                        else
                        {
                            var specialString = stringBuilder.ToString();
                            stringBuilder.Clear();
                            state = INITIAL_STATE;
                            switch (specialString)
                            {
                                case ":=":
                                    tokens.Add(Operation.Assign);
                                    break;
                                case ">":
                                    tokens.Add(Operation.Greater);
                                    break;
                                case "<":
                                    tokens.Add(Operation.Less);
                                    break;
                                case "<<":
                                    tokens.Add(Operation.Bsl);
                                    break;
                                case ">>":
                                    tokens.Add(Operation.Bsr);
                                    break;
                                default:
                                    tokens.Add(new Error(specialString));
                                    break;
                            }
                        }

                        break;
                    case NUMBER_STATE:
                        if (char.IsDigit(symbol))
                        {
                            stringBuilder.Append(symbol);
                            index++;
                        }
                        else
                        {
                            var numberString = stringBuilder.ToString();
                            stringBuilder.Clear();
                            tokens.Add(new Num(int.Parse(numberString)));
                            state = INITIAL_STATE;
                        }

                        break;
                    case ID_STATE:
                        if (char.IsLetter(symbol))
                        {
                            stringBuilder.Append(symbol);
                            index++;
                        }
                        else
                        {
                            var idString = stringBuilder.ToString();
                            stringBuilder.Clear();
                            state = INITIAL_STATE;
                            switch (idString)
                            {
                                case "IF":
                                    tokens.Add(new If());
                                    break;
                                case "Var":
                                    tokens.Add(new Var());
                                    break;
                                case "End":
                                    tokens.Add(new End());
                                    break;
                                case "ELSE":
                                    tokens.Add(new Else());
                                    break;
                                case "Begin":
                                    tokens.Add(new Begin());
                                    break;
//                                case ">>":
//                                    tokens.Add(Operation.Bsr);
//                                    break;
//                                case "<<":
//                                    tokens.Add(Operation.Bsl);
//                                    break;
                                default:
                                    var id = table.Add(idString);
                                    tokens.Add(new Id(id));
                                    break;
                            }
                        }

                        break;
                }
            }

            return tokens;
        }
    }
}