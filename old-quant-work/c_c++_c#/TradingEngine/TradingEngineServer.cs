using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;

using System;

using TradingEngine.Core.Configuration;
using TradingEngineServer.Logging;

namespace TradingEngine.Core
{
    // inheriting from ITradingEngineServer, which means it must override its methods
    sealed class TradingEngineServer : BackgroundService, ITradingEngineServer
    {
        private readonly IOptions<TradingEngineServerConfiguration> _engineConfiguration;
        private readonly ITextLogger _logger;

        public TradingEngineServer(IOptions<TradingEngineServerConfiguration> engineConfiguration, ILogger<TradingEngineServer> logger) 
        {
            _engineConfiguration = engineConfiguration ?? throw new ArgumentNullException(nameof(engineConfiguration));
            _logger = textLogger ?? throw new ArgumentNullException(nameof(textLogger));
        }

        // Task represents a long running operation
        // Here Run is passed to ExecuteAsync.
        public Task Run(CancellationToken token) => ExecuteAsync(token);

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            _logger.LogInformation($"Starting {nameof(TradingEngineServer)}");

            while (!stoppingToken.IsCancellationRequested)
            {

            }

            _logger.LogInformation($"Stopping {nameof(TradingEngineServer)}");

            return Task.CompletedTask;
        }
    }
}
