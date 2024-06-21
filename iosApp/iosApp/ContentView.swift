import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    @ObservedObject private var viewModel: ViewModel = .init()
    let greetings: Array<String> = ["aa", "ttt"]

    var body: some View {
        
        List(viewModel.greetings, id: \.self) { greeting in
            Text(greeting)
        }
        .task {
             await viewModel.startObserving()
        }
    }
}

extension ContentView {

    @MainActor
    class ViewModel: ObservableObject {
        @Published var greetings: Array<String> = []

        func startObserving() async {
            for await str in Greeting().greetFlow() {
                self.greetings.append(str)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
