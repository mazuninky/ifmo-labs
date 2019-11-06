using System.Collections;
using System.Collections.Generic;
using System.Reflection.Metadata;

namespace Scanner
{
    public struct TableRow
    {
        public TableRow(string name)
        {
            this.name = name;
        }

        public string name;
    }

    public class Table
    {
        public List<TableRow> table;

        public Table(List<TableRow> table)
        {
            this.table = table;
        }

        public Table()
        {
            this.table = new List<TableRow>();
        }

        public int Add(string id)
        {
            var index = -1;
            var count = 0;
            foreach (var tableRow in table)
            {
                if (tableRow.name == id)
                    index = count;
                count++;
            }

            if (index != -1) return index;
            
            table.Add(new TableRow(id));
            return table.Count - 1;
        }

        public TableRow Get(int position)
        {
            return table[position];
        }

        public TableRow this[int position] => Get(position);
    }
}