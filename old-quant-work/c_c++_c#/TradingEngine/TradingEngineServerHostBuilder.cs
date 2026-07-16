using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

using TradingEngine.Core.Configuration;

// This is the service that will run indefinitely for our TradingEngine
namespace TradingEngine.Core
{
    public sealed class TradingEngineServerHostBuilder
    {
        public static IHost BuildTradingEngineServer()
            => Host.CreateDefaultBuilder().ConfigureServices((context, services) =>
            {
                // context allows us to index into our configuration

                // Starting with configuration...
                services.AddOptions();
                services.Configure<TradingEngineServerConfiguration>(context.Configuration.GetSection(nameof(TradingEngineServerConfiguration)));

                // singleton objects?
                // Add singleton objects...
                services.AddSingleton<ITradingEngineServer, TradingEngineServer>();
                services.AddSingleton<ITextLogger, >();

                // Add hosted service
                services.AddHostedService<TradingEngineServer>();
            }).Build();

    }
}
