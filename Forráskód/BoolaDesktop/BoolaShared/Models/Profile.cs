using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Profile
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public bool IsBusiness { get; set; }
        public string LanguageId { get; set; }
        public Guid ExpenseListId { get; set; } = Guid.Empty;
        public string AccountEmail { get; set; }

        public Profile(Guid id, string name, bool isBusiness, string languageId, Guid expenseListId, string accountEmail)
        {
            Id = id;
            Name = name;
            IsBusiness = isBusiness;
            LanguageId = languageId;
            ExpenseListId = expenseListId;
            AccountEmail = accountEmail;
        }

        public Profile()
        {
            Id = Guid.NewGuid();
            Name = "Standard";
            IsBusiness = false;
            LanguageId = "hu-hu";
            AccountEmail = string.Empty;
        }
        public override string ToString()
        {
            return "Teszt";
        }

    }
}
