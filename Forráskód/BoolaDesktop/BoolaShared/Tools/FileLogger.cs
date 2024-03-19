using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace BoolaShared.Tools
{
    internal static class FileLogger
    {
        internal static void LogObject(object obj)
        {
            var stream = new FileStream(obj.GetType().Name + ".txt",FileMode.Create);
            using var sw = new StreamWriter(stream);
            sw.WriteLine(JsonSerializer.Serialize(obj));
            sw.Flush();
            sw.Close();
        }
    }
}
