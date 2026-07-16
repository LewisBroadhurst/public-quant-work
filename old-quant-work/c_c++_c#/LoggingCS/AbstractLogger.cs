using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TradingEngineServer.Logging;

namespace TradingEngine.Logging
{
    public abstract class AbstractLogger : ILogger
    {
        protected AbstractLogger() { }

        public void Debug(string module, string message) => Log(LogLevel.Debug, module, message);
        public void Debug(string module, Exception exception) => Log(LogLevel.Debug, module, $"{exception}");

        public void Information(string module, string message) => Log(LogLevel.Info, module, message);
        public void Information(string module, Exception exception) => Log(LogLevel.Info, module, $"{exception}");

        public void Warning(string module, string message) => Log(LogLevel.Warn, module, message);
        public void Warning(string module, Exception exception) => Log(LogLevel.Warn, module, $"{exception}");

        public void Error(string module, string message) => Log(LogLevel.Error, module, message);
        public void Error(string module, Exception exception) => Log(LogLevel.Error, module, $"{exception}");

        protected abstract void Log(LogLevel level, string module,string message);
    }
}
