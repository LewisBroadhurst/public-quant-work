using System;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

using TradingEngine.Core;

// This is the entry point into the trading engine 
// It will build the dependency injection container and hosted service in one line

using var engine = TradingEngineServerHostBuilder.BuildTradingEngineServer();
TradingEngineServerServiceProvider.ServiceProvider = engine.Services;
{
    using var scope = TradingEngineServerServiceProvider.ServiceProvider.CreateScope();
    await engine.RunAsync().ConfigureAwait(false);
}