  function activateTile(tile, url) {
    if (!tile) return;

    tile.classList.add("glow");

    setTimeout(function() {
      tile.classList.remove("glow");
      window.location.href = url;
    }, 150);
  }
