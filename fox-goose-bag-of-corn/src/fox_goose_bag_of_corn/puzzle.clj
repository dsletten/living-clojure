(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn river-crossing-plan []
  (conj start-pos
        [[:fox :corn] [:boat :you :goose] []]
        [[:fox :corn] [:boat] [:you :goose]]
        [[:fox :corn] [:boat :you] [:goose]]
        [[:fox :corn :you] [:boat] [:goose]]
        [[:corn] [:boat :you :fox] [:goose]]
        [[:corn] [:boat] [:you :fox :goose]]
        [[:corn] [:boat :you :goose] [:fox]]
        [[:corn :goose :you] [:boat] [:fox]]
        [[:goose] [:boat :you :corn] [:fox]]
        [[:goose] [:boat] [:you :corn :fox]]
        [[:goose] [:boat :you] [:corn :fox]]
        [[:goose :you] [:boat] [:corn :fox]]
        [[] [:boat :goose :you] [:corn :fox]]
        [[] [:boat] [:corn :fox :goose :you]]))
        
