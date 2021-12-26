//
//  ErrorView.swift
//  iosApp
//
//  Created by Daniel Ávila on 21/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct ErrorView: View {
    private var msg: String
    private var onClick: () -> Void

    init(msg: String, onClick: @escaping () -> Void = {}) {
        self.msg = msg
        self.onClick = onClick
    }
    
    var body: some View {
        VStack {
            Text(msg)
            Button(action: onClick) {
                Text("Try Again")
                    .font(.title3)
                    .foregroundColor(.white)
                    .padding()
                    .background(Color.blue)
                    .cornerRadius(10)
                    .shadow(radius: 10)
            }
        }
    }
}
