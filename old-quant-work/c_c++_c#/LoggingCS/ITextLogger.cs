using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TradingEngine.Logging
{
    // Look more into IDisposable
    internal interface ITextLogger : ILogger, IDisposable
    {
    }
}
