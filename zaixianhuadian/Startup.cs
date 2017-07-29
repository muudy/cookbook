using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(zaixianhuadian.Startup))]
namespace zaixianhuadian
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
