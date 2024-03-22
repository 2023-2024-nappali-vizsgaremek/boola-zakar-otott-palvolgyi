using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Desktop.Models
{
    public class Profile
    {
        public Guid id { get; set; }
        public string name { get; set; }
        public bool isBusiness { get; set; }
        public string languageId { get; set; }
        public Guid expenseListId { get; set; } = Guid.Empty;
        public string accountEmail { get; set; }

        public Profile(Guid id, string name, bool isBusiness, string languageId, Guid expenseListId, string accountEmail)
        {
            this.id = id;
            this.name = name;
            this.isBusiness = isBusiness;
            this.languageId = languageId;
            this.expenseListId = expenseListId;
            this.accountEmail = accountEmail;
        }

        public Profile()
        {
            id = Guid.NewGuid();
            name = "Standard";
            isBusiness = false;
            languageId = "hu-hu";
            accountEmail = string.Empty;
        }
        public override string ToString()
        {
            return (isBusiness ? "Business " : "") + "Profile " + name + ", lang: " + languageId + ", for " + accountEmail;
        }

    }
}
