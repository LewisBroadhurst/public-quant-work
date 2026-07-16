using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TradingEngine.Logging.LoggingConfiguration
{
    class LoggingConfiguration
    {
        public LoggerType LoggerType { get; set; }

        public DatabaseLoggerConfiguration DatabaseLoggerConfiguration { get; set; }
        public TextLoggerConfiguration TextLoggerConfiguration { get; set; }
    }

    public class DatabaseLoggerConfiguration 
    {
    
    }

    class TextLoggerConfiguration
    {
        public string Directory { get; set; }
        public string FileName { get; set; }
        public string FileExtension { get; set; }
    }
}
