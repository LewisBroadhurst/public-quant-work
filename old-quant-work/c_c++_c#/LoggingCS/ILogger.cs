using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

// This is our logging interface
namespace TradingEngine.Logging
{
    internal interface ILogger
    {
        // module is where the code was called from
        void Debug(string module, string message);
        void Debug(string module, Exception exception);

        void Information(string module, string message);
        void Information(string module, Exception exception);

        void Warning(string module, string message);
        void Warning(string module, Exception exception);

        void Error(string module, string message);
        void Error(string module, Exception exception);
    }
}
