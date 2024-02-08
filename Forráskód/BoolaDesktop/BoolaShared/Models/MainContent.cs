using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public record MainContent(string WelcomeText, double SpendPercent, double SpendDelta, double MonthlySpend,
                                double Balance)
    {
        public MainContent() : this("Welcome", 0, 0, 0, 69)
        {
        }
    }
}
