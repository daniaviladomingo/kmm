//
//  CharactersViewModel.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 6/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

class CharactersVM: CharactersViewModel, ObservableObject {
    @Published var listCharacters: [Character] = []
    
    override init() {
        super.init()

        collect(flow: uiState, collect: { data in
            let state = (((data as! CharactersContractState).characters) as BasicUiState<NSArray>)
                    
            switch state {
                case let success as BasicUiStateSuccess<NSArray>:
                    self.listCharacters = success.data as! [Character]
                default:
                    break
                }
            }
        )
    }
}
