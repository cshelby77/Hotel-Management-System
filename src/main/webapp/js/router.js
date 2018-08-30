class Router {
    constructor(routes, el) {
        this.routes = routes;
        this.el = el;
        window.onhashchange = this.hashChanged.bind(this);
        this.hashChanged();
    }

    async hashChanged(ev) {
    //hashChanged(ev) {
        if(window.location.hash.length > 0) {
            const pageName = window.location.hash.substr(1);
            this.show(pageName);
        } else if (this.routes['#default']) {
            this.show('#default');
        }
    }

    async show(pageName) {
    //show(pageName) {
        const page = this.routes[pageName];
        await page.load();
        //page.load();
        this.el.innerHTML = '';
        page.show(this.el);
    }
}