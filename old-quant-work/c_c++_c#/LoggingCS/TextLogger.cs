using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TradingEngineServer.Logging;

namespace TradingEngine.Logging
{
    public class TextLogger : AbstractLogger, ITextLogger
    {
        protected override void Log(LogLevel level, string module, string message)
        {
            throw new NotImplementedException();
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }

    }
}
