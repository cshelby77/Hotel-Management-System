const r = new Router (
    {
        '#default': new Layout(new Page('gmenu.html'), new Page('ghome.html')),
        guest: new Layout(new Page('gmenu.html'), new Page('ghome.html')),
        greport: new Layout(new Page('gmenu.html'), new Page('gmessage.html')),
        gprofile: new Layout(new Page('gmenu.html')),
        makeres: new Layout(new Page('gmenu.html'), new Page('makeres.html')),
        host: new Layout(new Page('hmenu.html'), new Page('hhome.html'))
    },
    document.querySelector('main')
)