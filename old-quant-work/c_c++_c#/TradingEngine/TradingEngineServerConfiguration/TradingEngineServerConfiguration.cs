using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TradingEngine.Core.Configuration
{
    class TradingEngineServerConfiguration
    {
        public TradingEngineServerSettings TradingEngineServerSettings { get; set; }
    }

    class TradingEngineServerSettings
    {
        public int port { get; set; }
    }
}
