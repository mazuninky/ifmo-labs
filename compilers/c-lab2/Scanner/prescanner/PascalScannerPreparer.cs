using System.Text.RegularExpressions;

namespace Scanner
{
    public class PascalScannerPreparer : IScannerPreparer
    {
        public string Prepare(string input)
        {
            var newInput = input;
            var commentsRegex = new Regex(@"{[\s\S]*?}");
            var spaceRegex = new Regex(@"\s\s+"); //\s\s+|$[\s\s+] или \s\s+|\n
            newInput = commentsRegex.Replace(newInput, "");
           // newInput = newInput.Replace("\n", "");
            newInput = spaceRegex.Replace(newInput, " ");
            return newInput;
        }
    }
}